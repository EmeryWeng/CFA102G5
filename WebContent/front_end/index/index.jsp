<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <%@ include file="/front_end/commonCSS.file" %> <!-- 基本CSS檔案 -->
    </head>
    <body>
		<%@ include file="/front_end/loading.file" %> <!-- loading -->
        <%@ include file="/front_end/header.file" %> <!-- Header -->

        <!-- Banner Area -->
        <div class="banner-area">
            <div class="container">
                <div class="banner-content">
                    <h1>Feliz 提供您愉快的住宿體驗</h1>                        
                    <h1>開始您的旅程</h1>
                </div>
            </div>
        </div>
        <!-- Banner Area End -->

        <!-- Banner Form Area -->
        <div class="banner-form-area">
            <div class="container">
                <div class="banner-form">
                    <form>
                        <div class="row align-items-center">
                            <div class="col-lg-3 col-md-3">
                                <div class="form-group">
                                    <label>CHECK IN TIME</label>
                                    <div class="input-group">
                                        <input id="datetimepicker" type="text" class="form-control" placeholder="11/02/2020">
                                        <span class="input-group-addon"></span>
                                    </div>
                                    <i class='bx bxs-chevron-down'></i>	
                                </div>
                            </div>

                            <div class="col-lg-3 col-md-3">
                                <div class="form-group">
                                    <label>CHECK OUT TIME</label>
                                    <div class="input-group">
                                        <input id="datetimepicker-check" type="text" class="form-control" placeholder="11/02/2020">
                                        <span class="input-group-addon"></span>
                                    </div>
                                    <i class='bx bxs-chevron-down'></i>	
                                </div>
                            </div>

                            <div class="col-lg-2 col-md-2">
                                <div class="form-group">
                                    <label>ROOMS</label>
                                    <select class="form-control select-rooms">
                                        <option>01</option>
                                        <option>02</option>
                                        <option>03</option>
                                        <option>04</option>
                                        <option>05</option>
                                        <option>06</option>
                                        <option>07</option>
                                        <option>08</option>
                                    </select>	
                                </div>
                            </div>

                            <div class="col-lg-1 col-md-1">
                                <div class="form-group">
                                    <label>GUESTS</label>
                                    <select class="form-control select-guests">
                                        <option>01</option>
                                        <option>02</option>
                                        <option>03</option>
                                        <option>04</option>
                                    </select>	
                                </div>
                            </div>

                            <div class="col-lg-3 col-md-3 row justify-content-center">
                                <input type="hidden" name="action" value="">
                                <input class="btn btn-primary" type="submit" value="查看空房">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Banner Form Area End -->
        
        <!-- Room Testimonials Area Two  -->
        <div class="testimonials-area-one pt-100 pb-70 room-area-bg">
            <div class="container">
                <div class="section-title text-center">
                    <h2 class="area-title ">房型介紹</h2>
                    <hr>
                    <h3 class="area-subtitle">恬靜舒適的居住空間房與細緻用心的服務，提供您卓越的住宿體驗。</h3>
                </div>
                <div class="testimonials-slider-two owl-carousel owl-theme pt-45">
                    <div class="testimonials-style">
                        <div class="row align-items-center">
                            <div class="col-lg-7">
                                <div class="testimonials-img">
                                    <img src="<%=request.getContextPath()%>/front_end/assets/img/2_1.jpg" alt="Images">
                                </div>
                            </div>
        
                            <div class="col-lg-5">
                                <div class="testimonials-content">
                                    <h2>行政套房</h2>
                                    <p>
                                        精心設計的室內格局堅持絕對自在的空間感，大沙發供您休憩或會面訪客，寬闊更衣間供您收納衣物與配件，二人入住也能享有獨立空間，如居家般無拘無束，無論商務或渡假的旅客都能充分運用。
                                    </p>
                                    <form>
                                        <input type="hidden" name="type_no"  value="${roomTypeVO.type_no}">
                                        <input type="hidden" name="action" value="delete">
                                        <input class="btn btn-primary" type="submit" value="了解更多">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="testimonials-style">
                        <div class="row align-items-center">
                            <div class="col-lg-7">
                                <div class="testimonials-img">
                                    <img src="<%=request.getContextPath()%>/front_end/assets/img/1_2.jpg" alt="Images">
                                </div>
                            </div>
        
                            <div class="col-lg-5">
                                <div class="testimonials-content">
                                    <h2>豪華客房</h2>
                                    <p>
                                        大坪數的寬敞讓您自在入住，寬敞空間適合協同伴侶入住，同時享受獨處與兩人時光。浴室備有高級義大利品牌香氛備品，細心關照您的需求，給您無可取代的精緻體驗。
                                    </p>
                                    <form>
                                        <input type="hidden" name="type_no"  value="${roomTypeVO.type_no}">
                                        <input type="hidden" name="action" value="delete">
                                        <input class="btn btn-primary" type="submit" value="了解更多">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Testimonials Area Two End -->
        
        <!-- Activity Area -->
        <div class="index-activity-bg room-area pt-100 pb-70">
            <div class="container">
                <div class="section-title text-left">
                    <h2 class="area-title ">熱門活動</h2>
                    <hr>
                    <h3 class="area-subtitle">探索花蓮的各種特色活動。<br>我們提供超值優惠、精選旅遊攻略與人氣玩樂。<br>推薦您大家都在參加的熱門活動！</h3>
                </div>
                <div class="row pt-45 justify-content-end">
                    <div class="index-activity col-lg-3 col-md-5">
                        <div class="room-card">
                            <a href="活動內頁.html">
                                <img src="<%=request.getContextPath()%>/front_end/assets/img/room-img.jpg" alt="Images">
                                <div class="content">
                                    <i class='bx bxs-medal no1'></i>
                                    <h5>海洋賞鯨導覽體驗</h5>
                                    <div class="index-activity">
                                        <p>
                                            <i class='bx bxs-star'></i>
                                            4.7 (11則評價)
                                        </p>
                                        <p class="index-activity-price">NTD$ 1,000</p> 
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>

                    <div class="index-activity col-lg-3 col-md-5">
                        <div class="room-card">
                            <a href="活動內頁.html">
                                <img src="<%=request.getContextPath()%>/front_end/assets/img/room-img.jpg" alt="Images">
                                <div class="content">
                                    <i class='bx bxs-medal no2'></i>
                                    <h5>海洋賞鯨導覽體驗</h5>
                                    <div class="index-activity">
                                        <p>
                                            <i class='bx bxs-star'></i>
                                            4.4 (8則評價)
                                        </p>
                                        <p class="index-activity-price">NTD$ 900</p> 
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>

                    <div class="index-activity col-lg-3 col-md-5">
                        <div class="room-card">
                            <a href="活動內頁.html">
                                <img src="<%=request.getContextPath()%>/front_end/assets/img/room-img.jpg" alt="Images">
                                <div class="content">
                                    <i class='bx bxs-medal no3'></i>
                                    <h5>海洋賞鯨導覽體驗</h5>
                                    <div class="index-activity">
                                        <p>
                                            <i class='bx bxs-star'></i>
                                            4.8 (9則評價)
                                        </p>
                                        <p class="index-activity-price">NTD$ 1,100</p> 
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Activity Area End -->
        
        <!-- Food Area -->
        <div class="reservation-area pt-100 pb-70">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-6">
                        <div class="reservation-content">
                            <div class="section-title text-center">
                                <h2 class="area-title ">美食地圖</h2>
                                <hr>
                                <h3 class="area-subtitle text-left">飯店周邊的美食店家，在地人推薦的特色花蓮美食！<br>好山好水又靠海的花蓮，除了豐富的名勝跟景點讓人玩不膩，花蓮美食更是多到數不清。</h3>
                            </div>
                            <a href="food.html" class="default-btn btn-bg-one">更多周邊美食</a>
                        </div>
                    </div>
                    
                    <div class="col-lg-6">
                        <div class="reservation-img">
                        	<img src="<%=request.getContextPath()%>/front_end/assets/img/index_food1.jpg">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Food Area End -->

        <%@ include file="/front_end/message.file" %> <!-- Message --> 
        <%@ include file="/front_end/footer.file" %> <!-- Footer -->      
        <%@ include file="/front_end/commonJS.file" %> <!-- 基本JS檔案 -->
        <script>
//         	$(`.nav-item:nth-child(3)>a`).attr('class', 'active');
        </script>      
        
    </body>
</html>
