package org.jugbd.mnet.utils;
 import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
public class Either {

 protected  L leftValue;

 protected  R rightValue;


@Override
public T fold(Function<L,T> transformLeft,Function<R,T> transformRight){
    return transformLeft.apply(this.leftValue);
}


public Either<L,R> either(Supplier<L> leftSupplier,Supplier<R> rightSupplier){
    return Optional.ofNullable(rightSupplier.get()).map(Either::<L, R>right).orElseGet(() -> Either.<L, R>left(leftSupplier.get()));
}


@Override
public boolean isRight(){
    return false;
}


@Override
public R getRight(){
    throw new NoSuchElementException("Tried to getRight from Left");
}


@Override
public boolean isLeft(){
    return true;
}


public Either<L,R> left(L left){
    return new Left<>(left);
}


@Override
public int hashCode(){
    return this.leftValue.hashCode();
}


@Override
public boolean equals(Object obj){
    if (obj instanceof Left<?, ?>) {
        final Left<?, ?> objAsLeft = (Left<?, ?>) obj;
        return this.leftValue.equals(objAsLeft.leftValue);
    } else {
        return false;
    }
}


public Either<L,R> right(R right){
    return new Right<>(right);
}


@Override
public Either<T,U> map(Function<L,T> transformLeft,Function<R,U> transformRight){
    return Either.<T, U>left(transformLeft.apply(this.leftValue));
}


@Override
public L getLeft(){
    return leftValue;
}


}