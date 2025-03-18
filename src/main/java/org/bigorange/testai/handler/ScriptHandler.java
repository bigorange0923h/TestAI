package org.bigorange.testai.handler;

import org.bigorange.testai.common.ExecutionResult;
import org.bigorange.testai.constants.LanguageType;
import org.springframework.stereotype.Component;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 脚本处理
 */
@Component
public class ScriptHandler {

    private static Pattern pattern = Pattern.compile("public\\s+class\\s+(\\w+)");

    /**
     * 执行脚本
     *
     * @return
     */
    public ExecutionResult execute(LanguageType language, String codeScript) {
        long startTime = System.currentTimeMillis();
        String output;
        try {
            switch (language) {
                case JAVA:
                    output = executeJava(codeScript);
                    break;
                case JAVASCRIPT:
                    output = executeJavaScript(codeScript);
                    break;
                default:
                    output = executeJava(codeScript);
                    break;
            }
            long endTime = System.currentTimeMillis();
            return ExecutionResult.success(output, endTime - startTime);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            return ExecutionResult.fail(e.getMessage(), endTime - startTime);
        }
    }

    /**
     * todo 执行JavaScript 脚本
     *
     * @param codeScript
     * @return
     */
    private String executeJavaScript(String codeScript) {
        return "暂不支持此脚本";
    }

    /**
     * 获取或创建目录
     *
     * @return
     */
    private static File mkdir() {
        // 获取当前项目的根目录
        String projectRoot = System.getProperty("user.dir");
        // 在根目录下创建 generated-code 文件夹
        String outputDir = projectRoot + "/generated-code";
        File dir = new File(outputDir);
        if (!dir.exists()) {
            dir.mkdirs(); // 如果文件夹不存在，创建文件夹
        }
        return dir;
    }

    /**
     * 获取生成的代码类名
     *
     * @param code
     * @return
     */
    private static String getClassName(String code) {
        // 正则表达式匹配类名
        Matcher matcher = pattern.matcher(code);
        if (matcher.find()) {
            return matcher.group(1); // 返回匹配的类名
        }
        throw new IllegalArgumentException("无法从代码中提取类名");
    }

    // Java动态编译执行
    public static String executeJava(String code) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, MalformedURLException {
        // 创建或获取当前项目下的生成目录
        File dir = mkdir();
        // 动态生成类名和文件名
        String className = "GeneratedClass_" + System.currentTimeMillis();
        String fileName = className + ".java";
        code = code.replace(getClassName(code), className);

        // 将代码写入 generated-code 文件夹中的文件
        File sourceFile = new File(dir, fileName);
        try (FileWriter writer = new FileWriter(sourceFile)) {
            writer.write(code);
            System.out.println("代码已写入文件: " + sourceFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("代码写入文件失败");
        }

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int compileResult = 0;
        // 编译代码
        compileResult = compiler.run(null, null, null, sourceFile.toString());
        if (compileResult != 0) {
            throw new RuntimeException("编译失败");
        }
        // 动态加载并执行
        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{dir.toURI().toURL()});
        Class<?> clazz = Class.forName(className, true, loader);
        Method method = clazz.getMethod("main", String[].class);
        method.invoke(null, (Object) new String[]{});
        return null;
    }


}
