//package org.geektimes.base.context;
//
//import org.geektimes.base.function.ThrowableFunction;
//
//import javax.naming.Context;
//import javax.naming.NameClassPair;
//import javax.naming.NamingEnumeration;
//import java.util.Collections;
//import java.util.LinkedList;
//import java.util.List;
//
//public class SearchComponentByJNDI {
//    private List<String> listAllComponentNames() {
//        return listComponentNames("/");
//    }
//
//    protected List<String> listComponentNames(String name) {
//        return executeInContext(context -> {
//            NamingEnumeration<NameClassPair> e = executeInContext(context, ctx -> ctx.list(name), true);
//
//            // 目录 - Context
//            // 节点 -
//            if (e == null) { // 当前 JNDI 名称下没有子节点
//                return Collections.emptyList();
//            }
//
//            List<String> fullNames = new LinkedList<>();
//            while (e.hasMoreElements()) {
//                NameClassPair element = e.nextElement();
//                String className = element.getClassName();
//                Class<?> targetClass = classLoader.loadClass(className);
//                if (Context.class.isAssignableFrom(targetClass)) {
//                    // 如果当前名称是目录（Context 实现类）的话，递归查找
//                    fullNames.addAll(listComponentNames(element.getName()));
//                } else {
//                    // 否则，当前名称绑定目标类型的话话，添加该名称到集合中
//                    String fullName = name.startsWith("/") ?
//                            element.getName() : name + "/" + element.getName();
//                    fullNames.add(fullName);
//                }
//            }
//            return fullNames;
//        });
//    }
//
//    /**
//     * 在 Context 中执行，通过指定 ThrowableFunction 返回计算结果
//     *
//     * @param function ThrowableFunction
//     * @param <R>      返回结果类型
//     * @return 返回
//     * @see ThrowableFunction#apply(Object)
//     */
//    protected <R> R executeInContext(ThrowableFunction<Context, R> function) {
//        return executeInContext(function, false);
//    }
//
//    /**
//     * 在 Context 中执行，通过指定 ThrowableFunction 返回计算结果
//     *
//     * @param function         ThrowableFunction
//     * @param ignoredException 是否忽略异常
//     * @param <R>              返回结果类型
//     * @return 返回
//     * @see ThrowableFunction#apply(Object)
//     */
//    protected <R> R executeInContext(ThrowableFunction<Context, R> function, boolean ignoredException) {
//        return executeInContext(this.envContext, function, ignoredException);
//    }
//
//}
