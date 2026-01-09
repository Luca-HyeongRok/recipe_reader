# RecipeReader - 데이터 구조 및 흐름

이 문서는 Recipe 및 Contacts 기능의 현재 데이터 구조와 흐름을 요약합니다.

## 구조 (요약)

- core/routing
  - 네비게이션 라우트 및 NavHost 구성
- data
  - datasource: 데이터 소스 (mock, permissions, Room)
  - resolver: ContentResolver 접근
  - repository: 분기 및 조합 로직
- domain/model
  - 프레젠테이션에서 사용하는 도메인 모델
- presentation
  - 화면, ViewModel, Contract
- ui
  - 공통 UI 컴포넌트와 테마

## Recipe 흐름

- UI
  - presentation/recipe/RecipeScreen -> RecipeViewModel
  - 상태: RecipeUiState
- ViewModel
  - presentation/recipe/RecipeViewModel
  - RecipeRepository.getBookmarkedRecipes() 호출
- Repository
  - data/repository/RecipeRepositoryImpl
  - RecipeContentDataSource에서 북마크 id 조회
  - RecipeMockDataSource 전체 목록에서 id로 필터링
- Data sources
  - data/datasource/RecipeContentDataSource: 북마크 id 조회
  - data/datasource/RecipeMockDataSource: 샘플 레시피 제공

데이터 흐름:
UI -> ViewModel -> Repository -> (ContentDataSource + MockDataSource) -> UI state

## Contacts 흐름

- UI
  - presentation/contacts/ContactsScreen -> ContactsViewModel
  - 상태: ContactsUiState
- ViewModel
  - presentation/contacts/ContactsViewModel
  - init 시 loadContacts() 수행, 검색어 변경 시 필터링
- Repository
  - data/repository/ContactsRepositoryImpl
  - 권한 체크
  - 권한 허용 시 Resolver로 실제 연락처 로드
  - 권한 없음 / 결과 없음이면 Mock 데이터 사용
  - Room 즐겨찾기 정보를 합쳐 Contact.isFavorite 반영
- Data sources / resolver
  - data/resolver/ContactsResolver: ContentResolver 조회
  - data/datasource/ContactsMockDataSource: 샘플 연락처 제공
  - data/datasource/ContactsPermissionDataSource: READ_CONTACTS 체크
  - data/datasource/ContactsDatabase + FavoriteContactDao:
    즐겨찾기 Room 저장소

데이터 흐름:
UI -> ViewModel -> Repository -> (Resolver 또는 Mock) + (Room 즐겨찾기) -> UI state

