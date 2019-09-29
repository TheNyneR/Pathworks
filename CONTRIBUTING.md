# Contributing
Below is some samples and basic questions and answers that you will need to
adhere to when contributing to this project.

## Work on issues
All pull requests need to be based on an issue. Either by picking one of the
open issues or submitting your own.  When submitting your own issue almost
anything is ok to suggest but maintainers might chose not to include your code
at their discretion. Issues regarding refactoring or just changing something
that you think can be done better are fine.  There aren't really any guidelines
for what makes a good or a bad issue.

### Issue length (time consumption)
Maintainers will try to keep the average work time required on one issue down
to roughly two hours. Larger tasks will be split into multiple issues. This is
just to make the project more accessible to newcomers.

### Assigning issues
Issues that are assigned to a milestone will not be assigned to you upon
request. You are more then welcome to start working on it and submitting a PR.
If you are the first submitter your code will most likely be merged after a
successful review.

The exception is maintainers assigning issues to themselves.

This is to prevent dangling issues that remain assigned without any information
about progress. This tends to turn new contributors away if all issues are
assigned.

At the odd chance of a double PR for one issue by two different contributors
don't feel disheartened if yours didn't get picked. Hopefully you still enjoyed
and learned something when writing the code.

Issues that are submitted by you and not assigned to a milestone can be
assigned to you on request.

### Commit messages
These should be in present tense, eg. "Adds a new algorithm" A PR commit should
reference the issue you are working on (eg. #4) in the short description. Eg.
"Implements #4"

### Code
Classes that have any amount of logic should have private fields and use
getters and setters for altering these values.  Fields should be final if it's
possible. Setters should be prefixed 'set' and getters can be prefixed 'get' or
'is' depending on what sounds better.

Example:
```java
public class Demo {

    private final int value;
    private boolean enabled;

    public Demo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
```

Classes that have no logic and only serve as data containers (think C structs) may have public fields.
Example:
```java
public class Position<T extends Number> {
    public T x;
    public T y;

    public Position(T x, T y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position<?> position = (Position<?>) o;
        return x.equals(position.x) &&
                y.equals(position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + "x" + y;
    }
}
```

### Only change what you are working on
Don't make changes that don't concern the issue you are addressing. The famous
"boy scout rule" is good but don't go beyond your issue.  A common cause for
these problems during review is "IDE auto fixing" or "IDE auto indentation". So
be careful.

The reason for this rule is that it can get really confusing when looking
through the history of a file and can hide the original author of the code that
is in question when using git blame for example.

## Conform to the code of conduct
Found [here](CODE_OF_CONDUCT.md)
