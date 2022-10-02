package tobystudyproject.tobystudyproject.calnum;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
