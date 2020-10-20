import java.util.ArrayList;
import java.util.Vector;


/**
 * @author Roshan Lamichhane
 */


public class MM {

        static int LEAF_SIZE = 1;
        public static int[][] ijkAlgorithmVector (Vector < Vector < Integer >> A,
                Vector < Vector < Integer >> B){
            int n = A.size();
            int[][] C = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        C[i][j] += A.get(i).get(k) * B.get(k).get(j);
                    }
                }
            }
            return C;
        }

        public static int[][] ijkAlgorithm (ArrayList < ArrayList < Integer >> A,
                ArrayList < ArrayList < Integer >> B){
            int n = A.size();

            int[][] C = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int k = 0; k < n; k++) {
                    for (int j = 0; j < n; j++) {
                        C[i][j] += A.get(i).get(k) * B.get(k).get(j);
                    }
                }
            }
            return C;
        }

        public static int[][] ikjAlgorithm (ArrayList < ArrayList < Integer >> A,
                ArrayList < ArrayList < Integer >> B){
            int n = A.size();

            int[][] C = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int k = 0; k < n; k++) {
                    for (int j = 0; j < n; j++) {
                        C[i][j] += A.get(i).get(k) * B.get(k).get(j);
                    }
                }
            }
            return C;
        }

        public static int[][] ikjAlgorithm ( int[][] A, int[][] B){
            int n = A.length;

            int[][] C = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int k = 0; k < n; k++) {
                    for (int j = 0; j < n; j++) {
                        C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
            return C;
        }


        private static int[][] add ( int[][] A, int[][] B){
            int n = A.length;
            int[][] C = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    C[i][j] = A[i][j] + B[i][j];
                }
            }
            return C;
        }

        private static int[][] subtract ( int[][] A, int[][] B){
            int n = A.length;
            int[][] C = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    C[i][j] = A[i][j] - B[i][j];
                }
            }
            return C;
        }


        private static int nextPowerOfTwo ( int n){
            int log2 = (int) Math.ceil(Math.log(n) / Math.log(2));
            return (int) Math.pow(2, log2);
        }


        private static int[][] strassen (ArrayList < ArrayList < Integer >> A,
                ArrayList < ArrayList < Integer >> B){
            int n = A.size();
            int m = nextPowerOfTwo(n);

            int[][] APrep = new int[m][m];
            int[][] BPrep = new int[m][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    APrep[i][j] = A.get(i).get(j);
                    BPrep[i][j] = B.get(i).get(j);
                }
            }

            int[][] CPrep = strassenR(APrep, BPrep);
            int[][] C = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    C[i][j] = CPrep[i][j];
                }
            }
            return C;
        }


        private static int[][] strassenR ( int[][] A, int[][] B){
            int n = A.length;

            if (n <= LEAF_SIZE) {
                return ikjAlgorithm(A, B);
            } else {
                int newSize = n / 2;
                int[][] a11 = new int[newSize][newSize];
                int[][] a12 = new int[newSize][newSize];
                int[][] a21 = new int[newSize][newSize];
                int[][] a22 = new int[newSize][newSize];

                int[][] b11 = new int[newSize][newSize];
                int[][] b12 = new int[newSize][newSize];
                int[][] b21 = new int[newSize][newSize];
                int[][] b22 = new int[newSize][newSize];


                int[][] aResult = new int[newSize][newSize];
                int[][] bResult = new int[newSize][newSize];

                for (int i = 0; i < newSize; i++) {
                    for (int j = 0; j < newSize; j++) {
                        a11[i][j] = A[i][j];
                        a12[i][j] = A[i][j + newSize];
                        a21[i][j] = A[i + newSize][j];
                        a22[i][j] = A[i + newSize][j + newSize];

                        b11[i][j] = B[i][j];
                        b12[i][j] = B[i][j + newSize];
                        b21[i][j] = B[i + newSize][j];
                        b22[i][j] = B[i + newSize][j + newSize];
                    }
                }

                aResult = add(a11, a22);
                bResult = add(b11, b12);
                int[][] p1 = strassenR(aResult, bResult);

                aResult = add(a21, a22);
                int[][] p2 = strassenR(aResult, b11);

                bResult = subtract(b12, b22);
                int[][] p3 = strassenR(a11, bResult);

                bResult = subtract(b21, b11);
                int[][] p4 = strassenR(a22, bResult);

                aResult = add(a11, a12);
                int[][] p5 = strassenR(aResult, b22);

                aResult = subtract(a21, a11);
                bResult = add(b11, b12);
                int[][] p6 = strassenR(aResult, bResult);

                aResult = subtract(a12, a22);
                bResult = add(b21, b22);
                int[][] p7 = strassenR(aResult, bResult);

                int[][] c12 = add(p3, p5);
                int[][] c21 = add(p2, p4);

                aResult = add(p1, p4);
                bResult = add(aResult, p7);
                int[][] c11 = subtract(bResult, p5);

                aResult = add(p1, p3);
                bResult = add(aResult, p6);
                int[][] c22 = subtract(bResult, p2);

                int[][] C = new int[n][n];
                for (int i = 0; i < newSize; i++) {
                    for (int j = 0; j < newSize; j++) {
                        C[i][j] = c11[i][j];
                        C[i][j + newSize] = c12[i][j];
                        C[i + newSize][j] = c21[i][j];
                        C[i + newSize][j + newSize] = c22[i][j];
                    }
                }
                return C;
            }
        }
    }
