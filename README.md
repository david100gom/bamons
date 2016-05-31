# Bamons

Bamons (Batch Monitoring System)는 Spring Batch 프레임워크를 이용한 배치 프로세스를 모니터링하기 위한 오픈소스 프로젝트이다.
<br>
##### Goal
 * Batch Process 에 연관된 서버들의 상태(health check)를 모니터링 할수 있는 기능 추가
 * 통계 그래프 추가
<br><br><br>

##### Job 메인리스트
![Bamons](./document/image/bamons-1.png)
##### 특정 Job 구동선택
![Bamons](./document/image/bamons-2.png)
##### Job & Step 상세 내역
![Bamons](./document/image/bamons-3.png)
<br><br><br>
#### 개발 환경
* JDK 7 이상
* Sencha Cmd v6.1.2.15
* ext-6.0.1 GPL
* Spring 4.2.0.RELEASE
* Spring Batch 3.0.6.RELEASE
* Spring Integration 4.2.5.RELEASE

<br><br>
#### ExtJS 빌드 (예시)
1. c:\ext-6.0.1 폴더안에 아래의 폴더 생성
    <br> ext-6.0.1    (ext-6.0.1-gpl 버전 압축풀어 놓음)
    <br> bamons       (소스 생성을 위한 프로젝트폴더)

2. 기본소스 생성 컴파일<br>
   sencha -sdk c:\ext-6.0.1\ext-6.0.1 generate app classic SpringBatch ./bamons

3. 개발 환경 컴파일 (bamons 폴더에서)<br>
   sencha app build development

4. checkout 한 bamons 하위의 app 폴더를 복사해서, 3번에서 생성한 개발환경 소스에 오버라이트한다.<br>

5. 개발을 완료되면 서비스 환경 컴파일 (bamons 폴더에서) 하여 production 파일들만 이관하여 서비스한다.<br>
   sencha app build production
