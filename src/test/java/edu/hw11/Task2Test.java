package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @Disabled
    void testRedefineExistedClass() throws Exception {
        class ArithmeticUtils {
            public int sum(int a, int b) {
                return a + b;
            }
        }

        class ArithmeticUtilsReload {
            public int sum(int a, int b) {
                return a * b;
            }
        }

        ByteBuddyAgent.install();
        ArithmeticUtils arithmetic = new ArithmeticUtils();
        new ByteBuddy()
            .redefine(ArithmeticUtilsReload.class)
            .name(ArithmeticUtils.class.getName())
            .make()
            .load(
                ArithmeticUtils.class.getClassLoader(),
                ClassReloadingStrategy.fromInstalledAgent()
            );
        assertThat(arithmetic.sum(3, 3)).isEqualTo(9);
    }
}
