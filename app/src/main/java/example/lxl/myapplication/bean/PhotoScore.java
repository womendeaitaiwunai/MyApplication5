package example.lxl.myapplication.bean;

/**
 * Created on 2016/6/23.
 * By nesto
 */
public enum PhotoScore {
    HEAD("头部摆正"),
    LIGHT_ENOUGH("光照充足"),
    LIGHT_EVEN("光线均匀"),
    CLOTHES("服装突出");

    private String description;

    PhotoScore(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}