package org.jugbd.mnet.utils.Either;
 import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
public class Left extends Either<L, R>{

public Left(L left) {
    this.rightValue = null;
    this.leftValue = left;
}
@Override
public T fold(Function<L,T> transformLeft,Function<R,T> transformRight){
    return transformLeft.apply(this.leftValue);
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


@Override
public Either<T,U> map(Function<L,T> transformLeft,Function<R,U> transformRight){
    return Either.<T, U>left(transformLeft.apply(this.leftValue));
}


@Override
public L getLeft(){
    return leftValue;
}


}