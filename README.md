# Bamons

Bamons (Batch Monitoring System)는 Spring Batch 프레임워크를 이용한 배치 프로세스를 모니터링하기 위한 오픈소스 프로젝트이다.
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


#### ExtJS 빌드
1. E:\ext-6.0.1 폴더안에 아래의 폴더 생성
    <br> ext-6.0.1    (ext-6.0.1-gpl 버전 압축풀어 놓음)
    <br> bamons       (소스 생성을 위한 프로젝트폴더)

2. 기본소스 생성<br>
   sencha -sdk e:\ext-6.0.1\ext-6.0.1 generate app classic SpringBatch ./bamons

3. 개발 환경 컴파일<br>
   sencha app build development

4. PROD 환경 컴파일<br>
   sencha app build production