package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.Test;
import net.bytebuddy.jar.asm.MethodVisitor;
import java.lang.reflect.InvocationTargetException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    public long fib(int number) {
        if(number == 0){
            return 0;
        }
        if(number == 1){
            return 1;
        }
        return fib(number - 1) + fib(number - 2);
    }

    public static class FibByte implements ByteCodeAppender {
        @Override
        public Size apply(
            MethodVisitor mv,
            Implementation.Context context,
            MethodDescription methodDescription
        ) {
            Label l0 = new Label();
            Label l1 = new Label();
            mv.visitCode();
            //l0 -- if(n == 0)
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitJumpInsn(Opcodes.IFNE, l1);
            //l2 -- return 0;
            mv.visitInsn(Opcodes.LCONST_0);
            mv.visitInsn(Opcodes.LRETURN);
            //l1
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            //if(n == 1);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitInsn(Opcodes.ICONST_1);
            mv.visitJumpInsn(Opcodes.IF_ICMPGT, l0);
            //l4 -- return 0;
            mv.visitInsn(Opcodes.LCONST_1);
            mv.visitInsn(Opcodes.LRETURN);
            //l3
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            //calculate F(n-1)
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitInsn(Opcodes.ICONST_1);
            mv.visitInsn(Opcodes.ISUB);
            mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                methodDescription.getDeclaringType().getTypeName(),
                methodDescription.getName(),
                methodDescription.getDescriptor(),
                false
            );
            //calculate F(n-2)
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitInsn(Opcodes.ICONST_2);
            mv.visitInsn(Opcodes.ISUB);
            mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                methodDescription.getDeclaringType().getTypeName(),
                methodDescription.getName(),
                methodDescription.getDescriptor(),
                false
            );
            //return sum F(n-1)+F(n-2)
            mv.visitInsn(Opcodes.LADD);
            mv.visitInsn(Opcodes.LRETURN);
            mv.visitMaxs(5, 2);
            mv.visitEnd();

            return new Size(5, 2);
        }
    }

    @Test
    void fibByteCodeTest()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //arrange
        Class<?> dynamicClass = new ByteBuddy().subclass(Object.class)
            .name("Fibonacci")
            .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC)
            .withParameter(int.class)
            .intercept(new Implementation.Simple(new FibByte()))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
        Object dynamicInstance = dynamicClass.getDeclaredConstructor().newInstance();
        //act
        long act = (long) dynamicClass.getMethod("fib", int.class).invoke(dynamicInstance, 6);
        //assert
        assertThat(act).isEqualTo(8);
    }
}
