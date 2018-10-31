package com.ssy.pink.network.api.sina.script;

import java.io.Reader;


/**
 * @author ssy
 * @date 2018/10/25
 */
public interface Compilable {
    CompiledScript compile(String var1) throws ScriptException;

    CompiledScript compile(Reader var1) throws ScriptException;
}