package edu.causwict.restapi.utils;

import java.text.BreakIterator;
import java.util.Locale;

public class GraphemeLengthUtil {

    /**
     * 주어진 문자열의 Grapheme Cluster 개수를 반환합니다.
     * <br>
     * Grapheme Cluster란, 사용자가 인식하는 글자 단위를 의미합니다. 예를 들어 {@code 가}, {@code ❤️}는 각각 Grapheme Cluster를 이룹니다. 이를 이용하면 이모지의 개수를 문제 없이 구할 수 있습니다.
     * <br>
     * 자세한 설명은 <a href="https://unicode.org/reports/tr29/#Grapheme_Cluster_Boundaries">여기</a>를 참조해주세요.
     * <br>
     * 주의할 점: 일부 환경에서는 ZWJ 등의 규칙을 무시하여 의도하지 않은 값이 반환될 수 있습니다. 실험해본 결과, 제 컴퓨터 환경에서 {@code 👩‍👩‍👦‍👦}는 {@code 7}로 계산됩니다.
     *
     * @param str 대상 문자열
     * @return Grapheme 글자수
     */
    public static int getGraphemeLength(String str) {
        // 조사를 통해 새로 안 개념이라서, 자세히 설명을 추가하도록 하겠습니다.
        // Grapheme Cluster를 구하기 위해 이들의 경계를 구할 수 있는 Iterator 클래스인 BreakIterator를 이용합니다.
        // 이때, 글자의 개수에 관심이 있으므로 getCharacterInstance를 이용합니다.
        BreakIterator it = BreakIterator.getCharacterInstance(Locale.ROOT);

        // Iterator의 텍스트를 파라미터로 주어진 str로 설정합니다.
        it.setText(str);

        // 결과값을 저장합니다.
        int result = 0;

        // 다음 Grapheme Cluster가 있다면 탐색을 계속합니다. 만약, 더 이상 남은 Cluster가 없다면 탐색을 종료합니다.
        while(it.next() != BreakIterator.DONE) {
            result++;
        }

        return result;
    }
}
