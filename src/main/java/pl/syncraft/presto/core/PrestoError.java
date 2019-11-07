package pl.syncraft.presto.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Daniel Zawlocki
 * @date 2019/10/31
 */
@AllArgsConstructor
@Getter
@ToString
public class PrestoError {
    private String message;
}
