package org.jugbd.mnet.utils.Either;
 import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
public class Right extends Either<L, R>{

private Right(R right) {
    this.leftValue = null;
    this.rightValue = right;
}
@Override
public T fold(Function<L,T> transformLeft,Function<R,T> transformRight){
    return transformRight.apply(this.rightValue);
}


@Override
public boolean isRight(){
    return true;
}


@Override
public R getRight(){
    return this.rightValue;
}


@Override
public boolean isLeft(){
    return false;
}


@Override
public int hashCode(){
    return this.rightValue.hashCode();
}


@Override
public boolean equals(Object obj){
    if (obj instanceof Right<?, ?>) {
        final Right<?, ?> objAsLeft = (Right<?, ?>) obj;
        return this.rightValue.equals(objAsLeft.rightValue);
    } else {
        return false;
    }
}


@Override
public Either<T,U> map(Function<L,T> transformLeft,Function<R,U> transformRight){
    return Either.<T, U>right(transformRight.apply(this.rightValue));
}


@Override
public L getLeft(){
    throw new NoSuchElementException("Tried to getLeft from Right");
}


}