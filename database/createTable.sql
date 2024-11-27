
use  my_libary;

create table books (
	maSach varchar(15) primary key,
    tenSach varchar(255),
    tacGia varchar(255),
    nhaXuatBan varchar(255),
	soLuong int
);

ALTER TABLE books
ADD CONSTRAINT CHECK_SL1 CHECK (soLuong >= 0);

create table people (
	maDG varchar(15) primary key,
    hoTen varchar(255),
    gioiTinh varchar(255),
    ngaySinh date,
    doiTuong varchar(255)
);

create table borrow (
	maMuon int auto_increment primary key,
    maDG varchar(15),
    maSach varchar(15),
    ngayMuon date,
    soLuong int,
    tinhTrang int,
    foreign key(maDG) references people(maDG),
	foreign key(maSach) references books(maSach)
);
