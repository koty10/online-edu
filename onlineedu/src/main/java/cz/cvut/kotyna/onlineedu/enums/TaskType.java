package cz.cvut.kotyna.onlineedu.enums;

public enum TaskType {
    NORMAL("normal"),
    EXTRA("extra");

    private String label;

    private TaskType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
