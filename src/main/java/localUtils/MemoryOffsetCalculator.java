package localUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 计算页面偏移量和起始地址
 */
@Data
@AllArgsConstructor
public class MemoryOffsetCalculator {





    private enum Type {
        BYTE(1),
        K_BYTE(BYTE.getSize() * 1024),
        M_BYTE(K_BYTE.getSize() * 1024),
        G_BYTE(M_BYTE.getSize() * 1024),
        ;

        private final long size;

        private Type(long size) {
            this.size = size;
        }

        private long getSize() {
            return size;
        }
    }

    private long offset;
    private Type offsetType;

    private long pageSize;
    private Type pageSizeType;

    private long totalCount;

    private Type localType;

    public void printPageInfo() {
        printConfig();
        long currentStart = offset * offsetType.getSize();
        long currentEnd = offset + (pageSize * pageSizeType.getSize());
        int currentPage = 1;
        do{
            // 十六进制格式化打印页面信息
            System.out.printf("第%d页: %x - %x%n", currentPage, currentStart, currentEnd);
            currentStart = currentEnd;
            currentEnd = currentStart + (pageSize * pageSizeType.getSize());
            currentPage++;
        }while (currentPage <= totalCount);
        localType.getSize();
        System.out.println("this is a test file");
    }

    public void printConfig() {
        System.out.println("--------------------");
        System.out.println("offset: " + offset + " " + offsetType);
        System.out.println("pageSize: " + pageSize + " " + pageSizeType);
        System.out.println("--------------------");
    }

    public static void main(String[] args) {
        MemoryOffsetCalculator calculator = new MemoryOffsetCalculator(0, Type.M_BYTE, 16, Type.K_BYTE, 10,Type.K_BYTE);
        calculator.printPageInfo();
    }
}
