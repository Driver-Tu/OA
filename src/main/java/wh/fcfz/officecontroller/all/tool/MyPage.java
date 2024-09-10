package wh.fcfz.officecontroller.all.tool;



public class MyPage<T> {
    private Integer pageNum;
    private Integer pageSize;
    private T data;
    private Integer total;


    public MyPage(Integer pageNum, Integer pageSize, T data, Integer total) {
        this.pageNum = 1;
        this.pageSize = 20;
        this.data = data;
        this.total = total;
    }

    public MyPage() {
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }


}
