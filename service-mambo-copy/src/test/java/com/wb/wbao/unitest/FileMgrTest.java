package com.wb.wbao.unitest;

import com.h3c.common.utils.Constant;
import com.wb.wbao.dto.FileDTO;
import com.wb.wbao.server.file.FileMgr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileMgrTest {

    @Resource
    private FileMgr fileMgr;

    @Test
    public void testListFiles(){
        List<FileDTO> dtos = fileMgr.getFiles(Constant.USER_PERSONAL, true);

        System.out.println("===============start==============");
        dtos.forEach(System.out::println);
        System.out.println("===============end==============");

    }

    @Test
    public void testClearDir(){
        fileMgr.clearUserPersonalFiles();
    }
}
