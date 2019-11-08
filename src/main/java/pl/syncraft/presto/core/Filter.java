package pl.syncraft.presto.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/08
 */
@Getter
@Setter
@NoArgsConstructor
public class Filter<T> {
    public enum Operator {
        EQUALS("equals"),
        NOT_EQUALS("not_equals"),
        GREATER("greater"),
        LESSER("lesser"),
        GREATER_OR_EQUALS("greater_or_equals"),
        LESSER_OR_EQUALS("lesser_or_equals"),
        LIKE("like"),
        NOT_LIKE("not_like");

        String value;

        public String getValue() {
            return value;
        }

        private Operator(String value) {
           this.value = value;
        }
    }

    private T value;
    private Operator operator = Operator.EQUALS;

    public Filter(T value) {
        this.value = value;
    }

    public Filter(T value, Operator operator) {
        this.value = value;
        setOperator(operator);
    }

    public void setOperator(Operator operator) {
        if (operator == null) {
            this.operator = Operator.EQUALS;
        } else {
            this.operator = operator;
        }
    }

    public void setOperator(String operator) {
        switch (operator) {
            case ">":
                setOperator(Operator.GREATER);
                break;

            case ">:":
                setOperator(Operator.GREATER_OR_EQUALS);
                break;

            case "<":
                setOperator(Operator.LESSER);
                break;

            case "<:":
                setOperator(Operator.LESSER_OR_EQUALS);
                break;

            case "like":
                setOperator(Operator.LIKE);
                break;

            case "notlike":
                setOperator(Operator.NOT_LIKE);
                break;

            case "!:":
                setOperator(Operator.NOT_EQUALS);
                break;

            default:
                setOperator(Operator.EQUALS);
        }
    }

    @Override
    public String toString() {
        return "Filter(operator: " + operator.value + ", value: " + value + ")";
    }
}
