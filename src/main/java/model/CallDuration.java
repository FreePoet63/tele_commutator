package model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.AllArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * CallDuration class
 *
 * This class represents the call durations for both incoming and outgoing calls.
 *
 * @author razlivinsky
 * @since 22.03.2024
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class CallDuration {
    private long incomingDuration = 0;
    private long outgoingDuration = 0;

    /**
     * Adds call duration based on the call type.
     *
     * @param type     the type of the call
     * @param duration the duration of the call
     */
    public void addDuration(String type, long duration) {
        if ("01".equals(type)) {
            incomingDuration += duration;
        } else if ("02".equals(type)) {
            outgoingDuration += duration;
        }
    }

    /**
     * Gets the total duration of both incoming and outgoing calls.
     *
     * @return the total duration of both incoming and outgoing calls
     */
    public long getTotalDuration() {
        return incomingDuration + outgoingDuration;
    }

    /**
     * Converts the duration in seconds to a formatted representation of hours, minutes, and seconds.
     *
     * @param durationInSeconds the duration in seconds to be formatted
     * @return the formatted duration string in the "HH:MM:SS" format
     */
    private String formatDuration(long durationInSeconds) {
        long hours = TimeUnit.SECONDS.toHours(durationInSeconds);
        long minutes = TimeUnit.SECONDS.toMinutes(durationInSeconds) % 60;
        long seconds = durationInSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * Converts the call duration data to a JSON format.
     *
     * @param msisdn the MSISDN (phone number)
     * @return the call duration data in JSON format
     */
    public String toJson(String msisdn) {
        String formattedIncomingDuration = formatDuration(Math.abs(incomingDuration));
        String formattedOutgoingDuration = formatDuration(Math.abs(outgoingDuration));

        return String.format("{\n" +
                "    \"msisdn\": \"%s\",\n" +
                "    \"incomingCall\": {\n" +
                "        \"totalTime\": \"%s\"\n" +
                "    },\n" +
                "    \"outgoingCall\": {\n" +
                "        \"totalTime\": \"%s\"\n" +
                "    }\n" +
                "}", msisdn, formattedIncomingDuration, formattedOutgoingDuration);
    }
}

