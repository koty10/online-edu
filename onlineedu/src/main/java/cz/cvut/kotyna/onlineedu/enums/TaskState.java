package cz.cvut.kotyna.onlineedu.enums;

public enum TaskState {
    NEW {
        public String toString() {
            return "New";
        }
        public String getCzechToString() {
            return "Nový";
        }
    },
    SUBMITTED {
        public String toString() {
            return "Submitted";
        }
        public String getCzechToString() {
            return "Odevzdáno";
        }
    },
    ACCEPTED {
        public String toString() {
            return "Accepted";
        }
        public String getCzechToString() {
            return "Schváleno";
        }
    },
    RETURNED {
        public String toString() {
            return "Returned";
        }
        public String getCzechToString() {
            return "Vráceno";
        }
    }
}
