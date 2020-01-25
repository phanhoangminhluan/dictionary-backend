package com.luanphm.dictionarybackend.utility;

import com.luanphm.dictionarybackend.constant.DFConstant;
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
        sb.append(DFConstant.QUESTION_MARK);
        sb.append(this.letterPattern.length() != 0          ? DFConstant.AND_MARK + DFConstant.LETTER_PATTERN           + DFConstant.EQUAL_MARK + this.letterPattern        : DFConstant.EMPTY_STRING);
        sb.append(this.pronunciationPattern.length() != 0   ? DFConstant.AND_MARK + DFConstant.PRONUNCIATION_PATTERN    + DFConstant.EQUAL_MARK + this.pronunciationPattern : DFConstant.EMPTY_STRING);
        sb.append(this.partOfSpeech.length() != 0           ? DFConstant.AND_MARK + DFConstant.PART_OF_SPEECH           + DFConstant.EQUAL_MARK + this.partOfSpeech         : DFConstant.EMPTY_STRING);
        sb.append(this.letters.length() != 0                ? DFConstant.AND_MARK + DFConstant.LETTERS                  + DFConstant.EQUAL_MARK + this.letters              : DFConstant.EMPTY_STRING);
        sb.append(this.lettersMin.length() != 0             ? DFConstant.AND_MARK + DFConstant.LETTERS_MIN              + DFConstant.EQUAL_MARK + this.lettersMin           : DFConstant.EMPTY_STRING);
        sb.append(this.lettersMax.length() != 0             ? DFConstant.AND_MARK + DFConstant.LETTERS_MAX              + DFConstant.EQUAL_MARK + this.lettersMax           : DFConstant.EMPTY_STRING);
        sb.append(this.sounds.length() != 0                 ? DFConstant.AND_MARK + DFConstant.SOUNDS                   + DFConstant.EQUAL_MARK + this.sounds               : DFConstant.EMPTY_STRING);
        sb.append(this.soundsMax.length() != 0              ? DFConstant.AND_MARK + DFConstant.SOUNDS_MAX               + DFConstant.EQUAL_MARK + this.soundsMax            : DFConstant.EMPTY_STRING);
        sb.append(this.soundsMin.length() != 0              ? DFConstant.AND_MARK + DFConstant.SOUNDS_MIN               + DFConstant.EQUAL_MARK + this.soundsMin            : DFConstant.EMPTY_STRING);
        sb.append(this.syllables.length() != 0              ? DFConstant.AND_MARK + DFConstant.SYLLABLES                + DFConstant.EQUAL_MARK + this.syllables            : DFConstant.EMPTY_STRING);
        sb.append(this.syllablesMin.length() != 0           ? DFConstant.AND_MARK + DFConstant.SYLLABLES_MIN            + DFConstant.EQUAL_MARK + this.syllablesMin         : DFConstant.EMPTY_STRING);
        sb.append(this.syllablesMax.length() != 0           ? DFConstant.AND_MARK + DFConstant.SYLLABLES_MAX            + DFConstant.EQUAL_MARK + this.syllablesMax         : DFConstant.EMPTY_STRING);
        sb.append(this.limit.length() != 0                  ? DFConstant.AND_MARK + DFConstant.LIMIT                    + DFConstant.EQUAL_MARK + this.limit                : DFConstant.EMPTY_STRING);
        sb.append(this.page.length() != 0                   ? DFConstant.AND_MARK + DFConstant.PAGE                     + DFConstant.EQUAL_MARK + this.page                 : DFConstant.EMPTY_STRING);
        sb.append(this.frequencyMin.length() != 0           ? DFConstant.AND_MARK + DFConstant.FREQUENCY_MIN            + DFConstant.EQUAL_MARK + this.frequencyMin         : DFConstant.EMPTY_STRING);
        sb.append(this.frequencyMax.length() != 0           ? DFConstant.AND_MARK + DFConstant.FREQUENCY_MAX            + DFConstant.EQUAL_MARK + this.frequencyMax         : DFConstant.EMPTY_STRING);
        sb.append(this.hasDetails.length() != 0             ? DFConstant.AND_MARK + DFConstant.HAS_DETAILS              + DFConstant.EQUAL_MARK + this.hasDetails           : DFConstant.EMPTY_STRING);
        return sb.toString();
    }
}
