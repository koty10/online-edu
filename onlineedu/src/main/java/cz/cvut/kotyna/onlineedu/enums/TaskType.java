package cz.cvut.kotyna.onlineedu.enums;

public enum TaskType {
    NORMAL {
        public String toString() {
            return "normal";
        }
    },
    EXTRA {
        public String toString() {
            return "extra";
        }
    }
}
