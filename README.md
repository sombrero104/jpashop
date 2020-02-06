# jpashop
Spring Boot, JPA, Thymeleaf<br/>
<br/>

### Lombok 설정
롬복 설치 후..<br/>
Preferences > Build, Execution, Deployment > Compiler > Annotation Processors 에서<br/>
‘Enable annotation processing’ 체크.<br/>
<br/>

### H2 데이터베이스 설정
보안상의 이유로 h2를 설치해야만 콘솔을 실행할 수 있게 되었다.<br/>
<pre>
https://www.h2database.com 에서 다운로드 받은 설치파일의 압축을 푼 후<br/>
/Users/sombrero104/study/h2/bin 디렉토리에서 ./h2.sh 실행하면 콘솔이 실행됨.<br/>

아래처럼 실행된 콘솔의 URL을 localhost로 변경. <br/>
(jsessionid 키값은 유지한 상태로 해야함. 디비 파일 생성 때 세션 권한이 필요하기 때문에.) <br/>
http://192.168.0.2:8082/login.jsp?jsessionid=c548f5735cffb777c350f7ca596401bc <br/>
=> http://localhost:8082/login.jsp?jsessionid=c548f5735cffb777c350f7ca596401bc <br/>

JDBC URL에 'jdbc:h2:~/jpashop' 설정.(파일 모드 접근. 디비 파일을 생성하기 위함.)<br/>
(끝부분의 '~/jpashop'(~는 홈디렉토리 => /Users/sombrero104/jpashop)는 디비 파일이 생성될 경로이다.)<br/>
콘솔에서 JDBC URL 설정 후 연결 버튼을 누르면 /Users/sombrero104/ 경로에 jpashop.mv.db 파일이 생성된다.<br/>

디비 파일이 생성된 이후에는 JDBC URL을 'jdbc:h2:tcp://localhost/~/jpashop'로 바꿔서 연결해서 사용한다.(네트워크 모드 접근)<br/>
</pre><br/>

### jar로 실행(gradle로 빌드한 스프링부트 어플리케이션 jar 파일을 콘솔에서 띄우기.)
/Users/sombrero104/IdeaProjects/jpashop 에서 './gradlew clean build' 실행.<br/>
/Users/sombrero104/IdeaProjects/jpashop/build/libs/jpashop-0.0.1-SNAPSHOT.jar 파일이 생성됨.<br/>
'java -jar jpashop-0.0.1-SNAPSHOT.jar'로 실행하면 어플리케이션이 실행됨.<br/>
<br/><br/>

### 연관관계의 주인
연관관계에 있는 두 엔티티가 있을 때, 외래키가 변경될 경우, <br/>
어떤 엔티티의 값을 기준으로 변경할 것인지에 대해 기준이 되는 엔티티를 연관관계의 주인이라고 한다.<br/>
<br/><br/>

### 모든 연관관계는 지연로딩으로 설정!
#### * 즉시로딩(EAGER)
예를 들어 Order 테이블을 로딩할 때 Order와 연관된 다른 테이블들을 같이 한번에 로딩하는 것.<br/>
즉시로딩(EAGER)은 예측이 어렵고, 어떤 SQL이 실행될지 추적하기 어렵다.<br/>
특히 JPQL(JPA가 제공하는 쿼리)을 실행할 때 N+1 문제가 자주 발생한다.<br/>
(예를 들어 Order가 100건일 경우 Order 100건을 가져온 후에 연관된 Member를 가져오기 위해 100개의 쿼리를 한번 더 날린다.<br/> N+1 => 100+1)<br/>
실무에서 모든 연관관계는 지연로딩(LAZY)으로 설정해야 한다.<br/>
연관된 엔티티를 함께 DB에서 조회해야 하면, fetch join 또는 엔티티 그래프 기능을 사용한다.<br/>
@XToOne(OneToOne, ManyToOne) 관계는 기본이 즉시로딩(EAGER)이므로 직접 지연로딩(LAZY)으로 설정해야 한다.<br/>
<pre>
@ManyToOne(fetch = FetchType.LAZY)
@OneToOne(fetch = FetchType.LAZY)
</pre><br/>

### 컬렉션은 필드에서 초기화하자!
컬렉션은 필드에서 바로 초기화 하는 것이 안전하다.<br/>
null 문제에서 안전하다.<br/>
하이버네이트는 엔티티를 영속화 할 때, 컬랙션을 감싸서 하이버네이트가 제공하는 내장 컬렉션으로 변경한다. <br/>
만약 getOrders() 처럼 임의의 메서드에서 컬력션을 잘못 생성하면 하이버네이트 내부 메커니즘에 문제가 발생할 수 있다. <br/>
따라서 필드레벨에서 생성하는 것이 가장 안전하고, 코드도 간결하다.<br/>
<pre>
Member member = new Member();
System.out.println(member.getOrders().getClass());
em.persist(team);
System.out.println(member.getOrders().getClass());
//출력 결과
class java.util.ArrayList
class org.hibernate.collection.internal.PersistentBag
</pre><br/>

