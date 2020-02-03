# jpashop




### H2 데이터베이스 설정
보안상의 이유로 h2를 설치해야만 콘솔을 실행할 수 있게 되었다.<br/>
https://www.h2database.com 에서 다운로드 받은 설치파일의 압축을 푼 후<br/>
/Users/sombrero104/study/h2/bin 디렉토리에서 ./h2.sh 실행하면 콘솔이 실행됨.<br/>
아래처럼 실행된 콘솔의 URL을 localhost로 변경. <br/>
(jsessionid 키값은 유지한 상태로 해야함. 디비 파일 생성 때 세션 권한이 필요하기 때문에.) <br/>
http://192.168.0.2:8082/login.jsp?jsessionid=c548f5735cffb777c350f7ca596401bc <br/>
=> http://localhost:8082/login.jsp?jsessionid=c548f5735cffb777c350f7ca596401bc <br/>
<br/>
JDBC URL에 'jdbc:h2:~/jpashop' 설정.(파일 모드 접근. 디비 파일을 생성하기 위함.)<br/>
(끝부분의 '~/jpashop'(~는 홈디렉토리 => /Users/sombrero104/jpashop)는 디비 파일이 생성될 경로이다.)<br/>
콘솔에서 JDBC URL 설정 후 연결 버튼을 누르면 /Users/sombrero104/ 경로에 jpashop.mv.db 파일이 생성된다.<br/>
JDBC URL을 'jdbc:h2:tcp://localhost/~/jpashop'로 바꿔서 연결해서 사용한다.(네트워크 모드 접근)<br/>
<br/><br/>




