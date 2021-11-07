
select * from instructor;
select * from instructor_detail;

delete from instructor where id>0;
delete from instructor_detail where id>0;
commit;
ALTER TABLE `hb-01-one-to-one-uni`.instructor AUTO_INCREMENT=3;
ALTER TABLE `hb-01-one-to-one-uni`.instructor_detail AUTO_INCREMENT=3;