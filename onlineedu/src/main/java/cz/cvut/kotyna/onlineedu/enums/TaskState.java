package cz.cvut.kotyna.onlineedu.enums;

public enum TaskState {
    NEW {
        public String toString() {
            return "new";
        }
    },
    SUBMITTED {
        public String toString() {
            return "submitted";
        }
    },
    ACCEPTED {
        public String toString() {
            return "accepted";
        }
    },
    RETURNED {
        public String toString() {
            return "returned";
        }
    },
    RESUBMITTED {
        public String toString() {
            return "resubmitted";
        }
    }
}
