package model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.AllArgsConstructor;

/**
 * Subscriber class
 *
 * This class represents a subscriber with a mobile phone number (MSISDN).
 *
 * @author razlivinsky
 * @since 21.03.2024
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Subscriber {
    private String msisdn;
}