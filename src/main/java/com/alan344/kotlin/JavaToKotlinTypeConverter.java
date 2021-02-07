package com.alan344.kotlin;

import com.alan344.java.FullyQualifiedJavaType;

import java.util.HashMap;
import java.util.Map;

/**
 * This class converts Java types to Kotlin types. It is not meant to cover all cases.
 * The main purpose is to convert type calculated by the database introspector into appropriate
 * types for Kotlin - which means that it covers Java primitives, Strings, and primitive wrapper
 * classes as those Java types have replacements in Kotlin.
 *
 * @author Jeff Butler
 *
 */
public class JavaToKotlinTypeConverter {
    private JavaToKotlinTypeConverter() {}

    private static final Map<String, String> typeMap = new HashMap<>();

    static {
        // string
        typeMap.put("java.lang.String", "kotlin.String"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("java.lang.String[]", "kotlin.Array<kotlin.String>"); //$NON-NLS-1$ //$NON-NLS-2$

        // primitives
        typeMap.put("byte", "kotlin.Byte"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("char", "kotlin.Char"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("short", "kotlin.Short"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("int", "kotlin.Int"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("long", "kotlin.Long"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("float", "kotlin.Float"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("double", "kotlin.Double"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("boolean", "kotlin.Boolean"); //$NON-NLS-1$ //$NON-NLS-2$

        // primitive arrays
        typeMap.put("byte[]", "kotlin.ByteArray"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("char[]", "kotlin.CharArray"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("short[]", "kotlin.ShortArray"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("int[]", "kotlin.IntArray"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("long[]", "kotlin.LongArray"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("float[]", "kotlin.FloatArray"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("double[]", "kotlin.DoubleArray"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("boolean[]", "kotlin.BooleanArray"); //$NON-NLS-1$ //$NON-NLS-2$

        // primitive wrappers
        typeMap.put("java.lang.Byte", "kotlin.Byte"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("java.lang.Character", "kotlin.Char"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("java.lang.Short", "kotlin.Short"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("java.lang.Integer", "kotlin.Int"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("java.lang.Long", "kotlin.Long"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("java.lang.Float", "kotlin.Float"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("java.lang.Double", "kotlin.Double"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("java.lang.Boolean", "kotlin.Boolean"); //$NON-NLS-1$ //$NON-NLS-2$

        // primitive wrapper arrays
        typeMap.put("java.lang.Byte[]", "kotlin.Array<kotlin.Byte>"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("java.lang.Character[]", "kotlin.Array<kotlin.Char>"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("java.lang.Short[]", "kotlin.Array<kotlin.Short>"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("java.lang.Integer[]", "kotlin.Array<kotlin.Int>"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("java.lang.Long[]", "kotlin.Array<kotlin.Long>"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("java.lang.Float[]", "kotlin.Array<kotlin.Float."); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("java.lang.Double[]", "kotlin.Array<kotlin.Double>"); //$NON-NLS-1$ //$NON-NLS-2$
        typeMap.put("java.lang.Boolean[]", "kotlin.Array<kotlin.Boolean>"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public static FullyQualifiedKotlinType convert(FullyQualifiedJavaType javaType) {
        FullyQualifiedKotlinType kotlinType = convertBaseType(javaType);

        for (FullyQualifiedJavaType argument : javaType.getTypeArguments()) {
            kotlinType.addTypeArgument(convert(argument));
        }

        return kotlinType;
    }

    private static FullyQualifiedKotlinType convertBaseType(FullyQualifiedJavaType javaType) {
        String typeName = javaType.getFullyQualifiedNameWithoutTypeParameters();
        return new FullyQualifiedKotlinType(typeMap.getOrDefault(typeName, typeName));
    }
}
