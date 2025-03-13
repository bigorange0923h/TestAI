package org.bigorange.testai.handler;

import org.bigorange.testai.common.ExecutionResult;
import org.bigorange.testai.constants.LanguageType;
import org.springframework.stereotype.Component;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 脚本处理
 */
@Component
public class ScriptHandler {

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
                    output =  executeJava(codeScript);
                    break;
                case JAVASCRIPT:
                    output = executeJavaScript(codeScript);
                    break;
                default:
                    output =  executeJava(codeScript);
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
     * @param codeScript
     * @return
     */
    private String executeJavaScript(String codeScript) {
        return "暂不支持此脚本";
    }


    // Java动态编译执行
    private String executeJava(String code) {
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            // 使用临时目录存储生成的.java文件
            Path tempDir = Files.createTempDirectory("generatedCode");
            Path javaFile = tempDir.resolve("GeneratedTest.java");
            Files.write(javaFile,  code.getBytes(StandardCharsets.UTF_8));

            // 编译代码
            int compileResult = compiler.run(null, null, null, javaFile.toString());
            if (compileResult != 0) throw new RuntimeException("编译失败");

            // 动态加载并执行
            URLClassLoader loader = URLClassLoader.newInstance(new URL[]{tempDir.toUri().toURL()});
            Class<?> clazz = loader.loadClass("GeneratedTest");
            Method method = clazz.getMethod("main", String[].class);
            method.invoke(null, (Object) new String[]{});
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
