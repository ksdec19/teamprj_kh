package com.kh.cafesurfer.domain.common.code;


import com.kh.cafesurfer.web.Code;

import java.util.List;

public interface CodeDAO {

  /**
   * 상위코드
   * @param pcode 상위코드
   * @return 하위코드 & 디코드
   */
  List<Code> code(String pcode);

  /**
   * 모든 코드 반환
   * @return
   */
  List<CodeAll> codeAll();
}
