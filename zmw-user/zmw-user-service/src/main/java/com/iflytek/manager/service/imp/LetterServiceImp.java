package com.iflytek.manager.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.iflytek.common.enumeration.user.LetterStatus;
import com.iflytek.common.model.Letter;
import com.iflytek.manager.api.LetterService;
import com.iflytek.manager.dao.LetterDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
@Service
public class LetterServiceImp implements LetterService {
    @Autowired
    private LetterDao letterDao;

    @Override
    public int writeLetter(Letter letter) {
        return letterDao.insertLetter(letter);
    }

    @Override
    public List<Letter> getLetterList(final long startNum, final int pageSize, final String mwId, final int isRead, final boolean bothLetter) {
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = -8189648198999217391L;
            {
                put("startNum", startNum);
                put("pageSize", pageSize);
                put("mwId", mwId);
                put("bothLetter", bothLetter);

            }
        };
        if (isRead != LetterStatus.All.getValue()) {
            map.put("isRead", isRead);
        }
        return letterDao.getLetterList(map);
    }

    @Override
    public int deleteLetter(String id) {
        return letterDao.deleteLetter(id);
    }

    @Override
    public int readLetter(final String id) {
        return letterDao.readLetter( id,LetterStatus.Already_Read.getValue());
    }

    @Override
    public int getLetterNumber(String mwId, boolean bothLetter) {
        return letterDao.getLetterNumber(mwId,bothLetter);
    }
}
