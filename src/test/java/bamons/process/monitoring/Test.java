package bamons.process.monitoring;

import org.springframework.util.StringUtils;

public class Test {

    // 테스트 파일
    public static void main(String[] args) throws Exception {
        String targetDate = "";
        boolean a = StringUtils.isEmpty(targetDate);

        System.out.print("RESULT : "+a);
    }
}
