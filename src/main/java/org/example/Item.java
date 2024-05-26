package org.example;
/*
  @author   george
  @project   gilded-rose
  @class  Item
  @version  1.0.0 
  @since 07.04.24 - 22.00
*/

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    public String name;
    public int sellIn;
    public int quality;

}
