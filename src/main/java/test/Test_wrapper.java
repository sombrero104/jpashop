package test;

public class Test_wrapper {

    public static void main(String[] args) {

        /**
         * Integer 같은 래퍼 클래스나 String과 같은 특수한 클래스는 공유 가능한 객체이지만 변경은 불가능하다.
         * https://gmlwjd9405.github.io/2019/09/12/value-type-of-basic-and-embedded.html
         */
        Integer a = new Integer(10);
        Integer b = a;
        // a.setValue(20);

    }

}
