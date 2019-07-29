-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 21, 2019 lúc 07:01 AM
-- Phiên bản máy phục vụ: 10.1.37-MariaDB
-- Phiên bản PHP: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `mystore.sql`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(11) NOT NULL,
  `madonhang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `tensanpham` varchar(100) NOT NULL,
  `giasanpham` int(10) NOT NULL,
  `soluongsanpham` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `tenkhachhang` varchar(200) NOT NULL,
  `sodienthoai` varchar(11) NOT NULL,
  `diachi` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `tenkhachhang`, `sodienthoai`, `diachi`) VALUES
(1, 'huyb', '012345679', '1234544'),
(4, 'huyen', '0123445656', '482');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(10) NOT NULL,
  `tenLoai` varchar(50) NOT NULL,
  `Hinh` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tenLoai`, `Hinh`) VALUES
(1, 'Bánh Kem', 'http://hoaviolet.com/image/banh-gato-hinh-tron-mau-hong-ebk22-15884j.jpg'),
(3, 'Bánh Ngọt', 'https://toinayangi.vn/wp-content/uploads/2016/02/Cach-lam-banh-cupcake-red-velvet-that-don-gian-1.jpg'),
(4, 'Bánh Cưới', 'https://bizweb.dktcdn.net/100/163/301/products/11-254ca631-4009-4c2e-9eac-21b39f73f803-df0b724b-6f9a-495d-abe8-eac4bfade8e9.png?v=1491456389293'),
(6, 'Quà Tặng', 'https://hinhanhdephd.com/wp-content/uploads/2017/12/hinh-anh-socola-valentine-de-thuong-ngay-valentine-3-356x220.jpg'),
(7, 'Đồ Uống', 'https://png.pngtree.com/element_origin_min_pic/16/11/01/c448247f5094036afbb027b5e4cd388f.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(10) NOT NULL,
  `TenSanPham` varchar(100) NOT NULL,
  `Gia` int(10) NOT NULL,
  `Hinh` varchar(300) NOT NULL,
  `MoTa` varchar(1000) NOT NULL,
  `IdLoai` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `TenSanPham`, `Gia`, `Hinh`, `MoTa`, `IdLoai`) VALUES
(1, 'TIRAMISU (Ổ TRÒN)', 330000, 'https://bizweb.dktcdn.net/thumb/grande/100/163/301/products/tiramisu-mousse-cake-banh-kem-y-quan-1.png?v=1520774638463', 'Cốt bánh: Bông lan cacao.\r\nMousse bánh: Phô mai mascarpone, trứng, kem tươi whipping, cà phê, rượu Rhum; Mặt bánh rắc bột cacao.\r\nVị bánh: bánh béo dịu,thơm socola và café, nồng nhẹ từ rượu.', 1),
(2, 'PASSION FRUIT MOUSSE - BÁNH MOUSSE CHANH DÂY', 295000, 'https://bizweb.dktcdn.net/thumb/grande/100/163/301/products/banh-kem-dolce-vita-mousse-chanh-day.png?v=1520773895827', 'Bánh làm từ bông lan vani; mousse gồm phô mai (cream cheese), tươi whipping (whipping cream), nước cốt chanh dây tươi.\r\nMặt bánh là lớp thạch làm từ gelatin và nước cốt chanh dây tươi.\r\nVị khá cân bằng: bánh béo vừa, chua và ngọt dịu, thơm mùi chanh dây.', 1),
(3, 'GREEN TEA LEMON MOUSSE - BÁNH MOUSSE TRÀ XANH CHAN', 295000, 'https://bizweb.dktcdn.net/thumb/grande/100/163/301/products/green-tea-lemon-mousse-banh-kem-pho-mai-tra-xanh-chanh-tiem-banh-dolce-vita-quan-1-3.png?v=1502190818667', 'Cốt bánh: bông lan trà xanh.\r\nMousse bánh: phô mai (cream cheese), kem tươi whipping (whipping cream) và nước cốt chanh tươi.\r\nMặt bánh: Lớp thạch làm từ Gelatine và nước cốt chanh tươi.\r\nVị bánh: Bánh béo vừa, chua và ngọt dịu, thơm dịu trà xanh và thơm vị chanh. Là một trong 2 loại bánh đặc trưng ở Dolce Vita.', 1),
(4, 'CHOCOLATE CHOUX', 28000, 'https://bizweb.dktcdn.net/thumb/grande/100/163/301/products/chocolate-choux-banh-su-kem-tiem-banh-kem-dolce-vita-quan-1-2.png?v=1503097292147', 'Quy cách: Hộp 4 chiếc.', 3),
(5, 'VANILLA CHOUX', 24000, 'https://bizweb.dktcdn.net/thumb/medium/100/163/301/products/vanilla-choux-banh-su-kem-tiem-banh-kem-dolce-vita-quan-3.png?v=1503093672013', 'Vui lòng đặt trước 2 ngày.\r\nSố lượng đặt tối thiểu: 4 hộp.\r\nQuy cách: Hộp 4 chiếc.', 3),
(6, 'BÁNH PHU THUÊ', 6000, 'https://danang.huongnghiepaau.com/images/mon-ngon-mien-trung/banh-phu-the.jpg', 'Bánh phu thê (hay được gọi chệch là bánh xu xê hoặc bánh xu xuê) là một loại bánh ngọt cổ truyền của Việt Nam.', 4),
(7, 'BÁNH CƯỚI HOA HỒNG TRẮNG', 1500000, 'https://www.marry.vn/wp-content/uploads/2019/01/14/14/banh-cuoi-floral-2.jpg', 'Kiểu bánh cưới hoa tươi đơn giản này thích hợp với các đám cưới phong cách rustic, đám cưới ngoài trời, những bữa tiệc buffet thân mật với ít khách mời. ', 4),
(8, 'BÁNH CƯỚI HOA TƯƠI', 1750000, 'https://www.marry.vn/wp-content/uploads/2019/01/14/14/banh-cuoi-floral-4.jpg', 'Kiểu bánh cưới hoa tươi đơn giản này thích hợp với các đám cưới phong cách rustic, đám cưới ngoài trời, những bữa tiệc buffet thân mật với ít khách mời. Chiếc bánh mộc mạc làm không gian thêm ấm cúng, kéo những vị khách lại gần nhau hơn.', 4),
(9, 'BROWNIE GIFT SET', 245000, 'https://bizweb.dktcdn.net/thumb/grande/100/163/301/products/3.png?v=1544504588033', 'Chỉ có 1 kích thước duy nhất: Kích thước 15cm x 6cm x cao 4cm\r\nVui lòng thông tin ở phần ghi chú về: Ngày và giờ bạn muốn nhận bánh, nội dung bạn muốn ghi lên bánh và bất kì thông tin nào bạn cần trao đổi với tiệm.', 6),
(10, 'SET QUÀ TẶNG CHRISTMAS - (ALMOND CHOCOLATE COOKIE - COOKIE SOCOLA HẠNH NHÂN)', 145000, 'https://bizweb.dktcdn.net/thumb/grande/100/163/301/products/dolce-vita-bakery-banh-cookie-giang-sinh-christmas-cake-quan-1-20.png?v=1512958654367', 'Vui lòng thông tin ở phần ghi chú về: Ngày và giờ bạn muốn nhận bánh, nội dung bạn muốn ghi lên bánh và bất kì thông tin nào bạn cần trao đổi với tiệm.', 6),
(11, 'Trà ô long cam đào dâu tây', 50000, 'https://jarvis.vn/uploaded/tra-o-long-cam-dao-dau-tay.jpg', 'Trà Ô Long có mùi thơm đậm, vị kéo dài sau khi uống, kết hợp với vị chua chua của dâu tây, vị ngọt nhẹ nhàng của cam và đào tất cả tạo nên một món đồ uống tinh tế và nhẹ nhàng.', 7),
(12, 'Trà đào cam sả', 35000, 'https://jarvis.vn/uploaded/tra-dao-cam-sa.jpg', 'đồ uống trà đào cam sả này là một chút biến tấu từ trà đào chanh sả nổi tiếng của Highlands Coffee. Khác với món chanh sả có vị chua nhẹ, thì trà đào cam sả có vị đậm ngọt nhiều hơn, thích hợp với những ai yêu thích đồ uống ngọt đậm đà.', 7),
(13, 'Trà hoa đậu biếc', 50000, 'https://jarvis.vn/uploaded/tra-hoa-dau-biec.jpg', ' loại trà được pha chế từ hoa lam điệp khô, với mùi thơm nhẹ và rất mát. Trà hoa đậu biếc nổi lên nhanh chóng vì màu sắc xanh biếc tuyệt đẹp', 7),
(14, 'Smoothies', 35000, 'https://jarvis.vn/uploaded/smoothies.jpg', 'smoothies là món đồ uống được pha chế từ nước ép trái cây, trái cây để lạnh, các loại si-rô hoặc hương liệu, đôi khi còn được thêm các nguyên liệu khác như socola, bơ, đậu phộng… để tạo sự phong phú trong mùi vị.', 7),
(15, 'BÁNH CƯỚI ĐƠN GIẢN-TINH TẾ', 1900000, 'https://www.marry.vn/wp-content/uploads/2019/01/14/14/banh-cuoi-floral-7.jpg', 'Mặc dù chiếc bánh đơn sắc nhưng không đơn điệu nhờ việc trang trí hoa văn cầu kỳ dọc thân. ', 4),
(16, 'MATCHA YOGURT MOUSSE - BÁNH MOUSSE TRÀ XANH SỮA CHUA', 295000, 'https://bizweb.dktcdn.net/thumb/grande/100/163/301/products/banh-kem-dolce-vita-mousse-matcha.png?v=1520774196667', 'Cốt bánh: bông lan vani.\r\nMousse bánh: phô mai (cream cheese), kem tươi whipping (whipping cream), sữa chua homemade và matcha. \r\nVị bánh: Bánh có vị béo, chua nhẹ và ngọt dịu, thơm vị trà; khi ăn có cảm giác “nhẫn nhẫn” của trà xanh.', 1),
(17, 'CHOCOLATE MOUSSE - BÁNH MOUSSE SOCOLA', 295000, 'https://bizweb.dktcdn.net/thumb/grande/100/163/301/products/chocolat-mousse-socola-dolce-vita-tiem-banh-kem-quan-1.png?v=1502444644483', 'Cốt bánh: bông lan cacao.\r\nMousse bánh: phô mai (cream cheese), kem tươi whipping (whipping cream) và socola. \r\nVị bánh: Ngọt ít, vị béo và đắng nhẹ của socola. Là loại bánh không thể bỏ qua cho tín đồ socola.', 1),
(18, 'Bánh Macaron', 120000, 'https://hd1.hotdeal.vn/images/13-10-2014/12%20banh%20camaron/10022_body_%20%285%29.jpg', 'Thưởng thức bánh macaron với 6 hương vị khác nhau.', 3);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
