package fundamentals_of_data_structures.sparse_vectors;

/**
 *
 * @author jameselder
 */
public class TestSparseNumericVector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SparseNumericVector X = new SparseNumericVector();
        System.out.println(X.getSize());
        SparseNumericVector Y = new SparseNumericVector();
        double projection;

        System.out.println("0. " + X.toString());
        X.add(new SparseNumericElement(100000, 3.1415));
        System.out.println("1. " + X.toString());
        X.add(new SparseNumericElement(15, 3));
        System.out.println("2. " + X.toString());
        X.add(new SparseNumericElement(1500, 3.14));
        System.out.println("3. " + X.toString());
        X.add(new SparseNumericElement(150, 3.1));
        System.out.println("4. " + X.toString());
        X.add(new SparseNumericElement(15000, 3.141));
        System.out.println("5. " + X.toString());

        
        Y.add(new SparseNumericElement(150000, 1));
        System.out.println("1. " + Y.toString());
        Y.add(new SparseNumericElement(15, 1));
        Y.add(new SparseNumericElement(15000, 1254));
        System.out.println("2. " + Y.toString());
        X.remove((long) 150);

        projection = X.dot(Y);

        System.out.println("The inner product of");
        System.out.print(X);
        System.out.println("and");
        System.out.print(Y);
        System.out.println("is ");
        System.out.printf("%.5f\n\n",projection); //answer should be 3*1 + 3.1415*1 = 6.1415
     }

}
