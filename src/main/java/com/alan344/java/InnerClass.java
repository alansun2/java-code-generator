package com.alan344.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class encapsulates the idea of an inner class - it has methods that make
 * it easy to generate inner classes.
 *
 * @author Jeff Butler
 */
public class InnerClass extends AbstractJavaType {

    private final List<TypeParameter> typeParameters = new ArrayList<>();

    private FullyQualifiedJavaType superClass;

    private boolean isAbstract;

    private final List<InitializationBlock> initializationBlocks = new ArrayList<>();

    private boolean isFinal;

    public InnerClass(FullyQualifiedJavaType type) {
        super(type);
    }

    public InnerClass(String type) {
        super(type);
    }

    public Optional<FullyQualifiedJavaType> getSuperClass() {
        return Optional.ofNullable(superClass);
    }

    public void setSuperClass(FullyQualifiedJavaType superClass) {
        this.superClass = superClass;
    }

    public void setSuperClass(String superClassType) {
        this.superClass = new FullyQualifiedJavaType(superClassType);
    }

    public List<TypeParameter> getTypeParameters() {
        return this.typeParameters;
    }

    public void addTypeParameter(TypeParameter typeParameter) {
        this.typeParameters.add(typeParameter);
    }

    public List<InitializationBlock> getInitializationBlocks() {
        return initializationBlocks;
    }

    public void addInitializationBlock(InitializationBlock initializationBlock) {
        initializationBlocks.add(initializationBlock);
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean isAbtract) {
        this.isAbstract = isAbtract;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }
}
