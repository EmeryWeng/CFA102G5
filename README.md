# CFA102G5
    .
    ├── .settings
    ├── WebContent
    │   ├── META-INF
    │   │   ├── MANIFEST.MF
    │   │   └── context.xml
    │   ├── WEB-INF
    │   │   ├── lib
    │   │   └── web.xml
    │   ├── 🖥️back_end 後台
    │   │   ├── 📁assets #後台共用的東西
    │   │   │   ├── css #共用的css、某頁的css
    │   │   │   │   ├── font-awesome
    │   │   │   │   ├── bootstrap.min.css
    │   │   │   │   ├── roomManage.css
    │   │   │   │   └── [...]
    │   │   │   ├── fonts #字型
    │   │   │   ├── images #共用的圖片、某頁的圖片
    │   │   │   │   ├── logo.png
    │   │   │   │   ├── roomBanner1.png
    │   │   │   │   └── [...]
    │   │   │   ├── js #共用的js、某頁的js
    │   │   │   │   ├── bootstrap.min.js
    │   │   │   │   ├── logout.js
    │   │   │   │   └── [...]
    │   │   │   └── plugins #套件，裡面有js和css
    │   │   │       ├── full-calendar
    │   │   │       ├── jquery-ui
    │   │   │       └── [...]
    │   │   │── 📁index #功能或區塊
    │   │   │   └── index.jsp
    │   │   └── 📁member #功能或區塊
    │   │       ├── images #資料庫裡的blob
    │   │       │   ├── mem001.png
    │   │       │   ├── mem002.png
    │   │       │   └── [...]
    │   │       ├── pages
    │   │       ├── text #資料庫裡的clob
    │   │       │   ├── mem001.txt
    │   │       │   ├── mem002.txt
    │   │       │   └── [...]
    │   │       ├── listAllmember.jsp
    │   │       └── updateMember.jsp
    │   └── 🛒front_end 前台
    │       └── [...] #架構參考上方的後台
    │           └── [...]
    ├── src
    │   └── com
    │       ├── ✏️roomImg #原本的table名稱ROOM_IMG拿掉底線改成camelCase
    │       │   ├── controller
    │       │   │   └── RoomImgServlet.java
    │       │   └── model
    │       │       ├── ✏️I_RoomImgDAO.java
    │       │       ├── ✏️RoomImgJDBCDAO.java
    │       │       ├── ✏️RoomImgVO.java
    │       │       ├── RoomImgJNDIDAO.java ✨未來    
    │       │       └── ✏️RoomImgService.java  
    │       └── [...]
    ├── .classpath
    ├── .gitignore
    ├── .project
    └── README.md
