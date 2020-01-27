package com.luanphm.dictionarybackend.utility;

import com.luanphm.dictionarybackend.constant.CommonConstants;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchEndpoint {
    private String letterPattern = "";
    private String pronunciationPattern = "";
    private String partOfSpeech = "";
    private String lettersMin = "";
    private String letters = "";
    private String lettersMax = "";
    private String soundsMax = "";
    private String sounds = "";
    private String soundsMin = "";
    private String syllables = "";
    private String syllablesMin = "";
    private String syllablesMax = "";
    private String limit = "";
    private String page = "";
    private String frequencyMin = "";
    private String frequencyMax = "";
    private String hasDetails = "";

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CommonConstants.QUESTION_MARK);
        sb.append(this.letterPattern.length() != 0          ? CommonConstants.AND_MARK + CommonConstants.LETTER_PATTERN           + CommonConstants.EQUAL_MARK + this.letterPattern        : CommonConstants.EMPTY_STRING);
        sb.append(this.pronunciationPattern.length() != 0   ? CommonConstants.AND_MARK + CommonConstants.PRONUNCIATION_PATTERN    + CommonConstants.EQUAL_MARK + this.pronunciationPattern : CommonConstants.EMPTY_STRING);
        sb.append(this.partOfSpeech.length() != 0           ? CommonConstants.AND_MARK + CommonConstants.PART_OF_SPEECH           + CommonConstants.EQUAL_MARK + this.partOfSpeech         : CommonConstants.EMPTY_STRING);
        sb.append(this.letters.length() != 0                ? CommonConstants.AND_MARK + CommonConstants.LETTERS                  + CommonConstants.EQUAL_MARK + this.letters              : CommonConstants.EMPTY_STRING);
        sb.append(this.lettersMin.length() != 0             ? CommonConstants.AND_MARK + CommonConstants.LETTERS_MIN              + CommonConstants.EQUAL_MARK + this.lettersMin           : CommonConstants.EMPTY_STRING);
        sb.append(this.lettersMax.length() != 0             ? CommonConstants.AND_MARK + CommonConstants.LETTERS_MAX              + CommonConstants.EQUAL_MARK + this.lettersMax           : CommonConstants.EMPTY_STRING);
        sb.append(this.sounds.length() != 0                 ? CommonConstants.AND_MARK + CommonConstants.SOUNDS                   + CommonConstants.EQUAL_MARK + this.sounds               : CommonConstants.EMPTY_STRING);
        sb.append(this.soundsMax.length() != 0              ? CommonConstants.AND_MARK + CommonConstants.SOUNDS_MAX               + CommonConstants.EQUAL_MARK + this.soundsMax            : CommonConstants.EMPTY_STRING);
        sb.append(this.soundsMin.length() != 0              ? CommonConstants.AND_MARK + CommonConstants.SOUNDS_MIN               + CommonConstants.EQUAL_MARK + this.soundsMin            : CommonConstants.EMPTY_STRING);
        sb.append(this.syllables.length() != 0              ? CommonConstants.AND_MARK + CommonConstants.SYLLABLES                + CommonConstants.EQUAL_MARK + this.syllables            : CommonConstants.EMPTY_STRING);
        sb.append(this.syllablesMin.length() != 0           ? CommonConstants.AND_MARK + CommonConstants.SYLLABLES_MIN            + CommonConstants.EQUAL_MARK + this.syllablesMin         : CommonConstants.EMPTY_STRING);
        sb.append(this.syllablesMax.length() != 0           ? CommonConstants.AND_MARK + CommonConstants.SYLLABLES_MAX            + CommonConstants.EQUAL_MARK + this.syllablesMax         : CommonConstants.EMPTY_STRING);
        sb.append(this.limit.length() != 0                  ? CommonConstants.AND_MARK + CommonConstants.LIMIT                    + CommonConstants.EQUAL_MARK + this.limit                : CommonConstants.EMPTY_STRING);
        sb.append(this.page.length() != 0                   ? CommonConstants.AND_MARK + CommonConstants.PAGE                     + CommonConstants.EQUAL_MARK + this.page                 : CommonConstants.EMPTY_STRING);
        sb.append(this.frequencyMin.length() != 0           ? CommonConstants.AND_MARK + CommonConstants.FREQUENCY_MIN            + CommonConstants.EQUAL_MARK + this.frequencyMin         : CommonConstants.EMPTY_STRING);
        sb.append(this.frequencyMax.length() != 0           ? CommonConstants.AND_MARK + CommonConstants.FREQUENCY_MAX            + CommonConstants.EQUAL_MARK + this.frequencyMax         : CommonConstants.EMPTY_STRING);
        sb.append(this.hasDetails.length() != 0             ? CommonConstants.AND_MARK + CommonConstants.HAS_DETAILS              + CommonConstants.EQUAL_MARK + this.hasDetails           : CommonConstants.EMPTY_STRING);
        return sb.toString();
    }
}
