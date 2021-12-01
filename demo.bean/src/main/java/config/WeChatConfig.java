package config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zml
 * @createDate 2021-07-19
 */
@Data
@Configuration
public class WeChatConfig {

    public static final String APP_ID = "wx6c543d3dce15460e";
    public static final String APP_SECRET = "7970fa3079dac2868e14592baaed4125";

    public static final String APP_ID_GZ = "wx6c543d3dce15460e";
    public static final String APP_SECRET_GZ = "5dc262e1abb7b8853243c6cc55b0ff97";

    public static final String APP_ID_WOO = "wx539ff5ec862826fc";
    public static final String APP_SECRET_WOO = "6deb201c51eb5bb69179c3bc524ea143";

}
