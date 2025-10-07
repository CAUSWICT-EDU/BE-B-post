# 개인 노트

---
### 요구사항
- **게시글 수정 api를 추가해주세요.**
  - 기존 [PostController](./src/main/java/edu/causwict/restapi/controller/PostController.java)에 PatchMapping으로 추가
- **게시글 리스트 api를 추가해주세요.** 
  - 기존 [PostController](./src/main/java/edu/causwict/restapi/controller/PostController.java)에 GetMapping으로 추가
- **제목이 비어 있는 경우에는 게시글 작성이 되지 않게 해주세요.**
  - [PostService](./src/main/java/edu/causwict/restapi/service/PostService.java)에서 제목 검사 후 예외 throw
  - 예외 이름 정해야 함
- **제목은 30자를 넘지 않게 해주세요.**
  - 위와 같이 처리
- **중복된 제목의 게시글은 작성되지 않게 해주세요.**
  - 위와 같이 처리
- **게시글 검색 기능을 추가해주세요. (예: 제목 키워드로 검색)**
  - 위에서 만든 게시글 리스트 api를 확장하여 쓸 예정
  - @RequestParam으로 파라미터를 받아서 검색
  - 파라미터 값이 null인 경우 단순 리스트 반환으로 간주
---
***선택과제***
- **ID 생성 책임을 따로 분리하여 유틸 클래스로 만들어주세요.**
  - 아마 게시글에 붙는 ID를 말하는 것으로 추정
  - 클래스 이름 작명해야 함
- **도배 방지: 새로운 게시글 작성은 마지막 작성 이후 3분 뒤에만 가능하도록 해주세요.**
  - ID 상으로 가장 마지막 게시글이 올라온 지 3분이 지난 후 작성 가능하도록?
- **게시글을 파일로 저장/불러오기 기능을 추가해주세요.**
  - 파일 포맷 먼저 구상해야 할듯
  - 불러올 때 포맷에 안 맞으면 예외 throw
- **게시글 제목에 이모지 허용, 이모지를 1글자 취급하여 30자 제한을 유지해주세요.**
  - 👍 ← 얘가 어떻게 처리되는지를 실험해봐야 할듯
  - 일반적인 문자처럼 처리되면 상관 X
---
## 키워드 과제
**1. SOLID 원칙이란 무엇인가요?**

유지보수하기 쉽고, 테스트 하기 쉬운 코드를 만드는 원칙
1. **Single Responsibility Principle**
   - 클래스는 하나의 책임만 가져야 함
   - ex) 저장 기능과 알림 기능을 서로 다른 클래스로 분리
2. **Open/Closed Principle**
   - 확장에는 열려 있고, 변경에는 닫혀 있어야 함
   - ex) 새로운 할인 정책이 생기면 인터페이스 등을 구현하여 확장, 인터페이스 자체를 변경하지는 X
3. **Liskov Substitution Principle**
   - 자식 클래스는 부모 클래스의 기능을 대체할 수 있어야 함
4. **Interface Segregation Principle**
   - 인터페이스는 클라이언트가 사용하지 않는 메서드에 의존하지 않아야 함
5. **Dependency Inversion Principle**
   - 고수준 모듈은 저수준 모듈에 의존하면 안되고, 추상에 의존해야 함

**2. Controller, Service, Repository, Domain 은 각각 어떤 역할을 하나요?**
- Controller : 
- Service : 
- Repository : 
- Domain : 

**3. RESTful 하다는 건 결국 어떤 의미인가요 ?**

**4. HTTP 통신에서 클라이언트와 서버는 어떻게 역할이 나뉠까요 ?**

**5. 자바 ``record`` 은 무엇인가요 ?**
- **불변(immutable) 데이터 객체**
- 모든 필드가 final로 선언된다고 보면 됨
- 불변 데이터 객체를 그냥 만들면 보일러플레이트 코드가 발생하는 문제를 해결
  - 보일러플레이트 코드: 변경되는 부분이 거의 없이 여러 곳에서 재사용 하는 코드
- 여러 메서드가 자동으로 생성됨
  - 생성자: 모든 필드를 포함
  - getter: ``.필드이름()``으로 생성
  - ``equals()``: 두 record가 같은지 확인, 필드와 record 비교
  - ``hashCode()``: 객체를 해시 값으로 변환, 내용이 같은 객체는 같은 해시 값을 가짐
  - ``toString()``: ``레코드이름[필드1, 필드2]``형식으로 반환