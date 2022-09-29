package tobystudyproject.tobystudyproject.test;

public interface LineCallbackWithGeneric<T> {
    T doSomethingWithLine(String line, T value);
}
