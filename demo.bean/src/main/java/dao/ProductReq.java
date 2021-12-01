package dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Zml
 * @createDate 2021-07-06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReq implements Serializable {

    private Integer productName;
}
