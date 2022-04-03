package org.gliderwiki.util;
 public class PageNavigation {

 private  boolean isPrevPage;

 private  boolean isNextPage;

 protected  int nowPage;

 protected  int rowTotal;

 protected  int blockList;

 protected  int blockPage;

 private  int totalPage;

 private  int startPage;

 private  int endPage;

 private  int startRow;

 private  int endRow;

// 페이지를 계산하는 생성자
public PageNavigation(int nowPage, int rowTotal, int blockList, int blockPage) {
    // super();
    // 각종 플래그를 초기화
    isPrevPage = false;
    isNextPage = false;
    // 입력된 전체 열의 수를 통해 전체 페이지 수를 계산한다
    this.totalPage = (int) Math.ceil((double) rowTotal / (double) blockList);
    // 현재 페이지가 전체 페이지수보다 클경우 전체 페이지수로 강제로 조정한다
    if (nowPage > this.totalPage) {
        nowPage = this.totalPage;
    }
    if (nowPage == 0) {
        nowPage = 1;
    }
    // DB입력을 위한 시작과 종료값을 구한다
    this.startRow = (int) (nowPage - 1) * blockList;
    this.endRow = (int) this.startRow + blockList - 1;
    // 시작페이지와 종료페이지의 값을 구한다
    this.startPage = (int) ((nowPage - 1) / blockPage) * blockPage + 1;
    this.endPage = (int) this.startPage + blockPage - 1;
    // 마지막 페이지값이 전체 페이지값보다 클 경우 강제 조정
    if (this.endPage > this.totalPage) {
        this.endPage = totalPage;
    }
    // 시작 페이지가 1보다 클 경우 이전 페이징이 가능한것으로 간주한다
    if (this.startPage > 1) {
        this.isPrevPage = true;
    }
    // 종료페이지가 전체페이지보다 작을경우 다음 페이징이 가능한것으로 간주한다
    if (this.endPage < this.totalPage) {
        this.isNextPage = true;
    }
    // 기타 값을 저장한다
    this.nowPage = nowPage;
    this.rowTotal = rowTotal;
    this.blockList = blockList;
    this.blockPage = blockPage;
    Debug();
}
public boolean isNextPage(){
    return isNextPage;
}


public boolean isPrevPage(){
    return isPrevPage;
}


public int getBlockSize(){
    return blockList;
}


public int getStartRow(){
    return startRow;
}


public int getEndRow(){
    return endRow;
}


public int getStartPage(){
    return startPage;
}


public int getEndPage(){
    return endPage;
}


public void Debug(){
    System.out.println("Total Page : " + this.totalPage + " / Start Page : " + this.startPage + " / End Page : " + this.endPage);
    System.out.println("Total Row : " + this.rowTotal + " / Start Row : " + this.startRow + " / End Row : " + this.endRow);
}


public int getTotalPage(){
    return totalPage;
}


}