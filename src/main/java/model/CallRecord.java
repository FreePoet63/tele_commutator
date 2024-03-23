package model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.AllArgsConstructor;

/**
 * CallRecord class
 *
 * This class represents a call record with details such as call type, MSISDN (phone number), start time, and end time.
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
public class CallRecord {
    private String type;
    private String msisdn;
    private long startTime;
    private long endTime;
}