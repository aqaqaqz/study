package view;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;


public class HelloWorld {
	
	// The window handle
	private long window;

	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");

		init();
		loop();

		// 콜백과 윈도우를 free(할당 해제하는걸로 추측)
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// GLFW와 에러콜백을 free
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	private void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		// 옵션 기본값으로 초기화.
		//해당 함수를 glfwWindowHint함수 아래 사용하면 적용된 옵션이 기본값으로 초기화
		glfwDefaultWindowHints(); 
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE); // the window will stay hidden after creation(뭔지 몰것음)
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // 화면크기 조절 가능여부

		// Create the window
		window = glfwCreateWindow(300, 300, "Hello World!", NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		// 키 콜백 셋업(down, up둘다 여기로 들어옴)
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // 하단 랜더링 loop에서 감지
		});

		// Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);
	}

	private void loop() {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		// http://forum.lwjgl.org/index.php?PHPSESSID=v1usairlevjkcg9ba4k0ijn5e1&topic=6858.0
		GL.createCapabilities();

		//glClear함수를 이용하여 버퍼를 지울때 기본 색상 변경(RGBA)
		//while의 glClear를 실행하지 않으면 검정화면 노출
		//glClear만 주석하고 glfwSwapBuffers를 실행하면 검정색인걸 보면 default는 검정
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

		// 종료하기 전까지 계속 렌더링을 한다
		// Esc키를 입력하면 종료된다 (glfwSetKeyCallback 에서 설정)
		while ( !glfwWindowShouldClose(window) ) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); //지정한 버퍼 지우기
			/*
			 * GL_COLOR_BUFFER_BIT		컬러 쓰기를 위해 현재 활성화된 버퍼
			 * GL_DEPTH_BUFFER_BIT		깊이 버퍼
			 * GL_ACCUM_BUFFER_BIT		누적 버퍼
			 * GL_STENCIL_BUFFER_BIT	스텐실 버퍼
			 */

			glfwSwapBuffers(window); // window의 버퍼를 변경한다.

			// 현재 이벤트큐에 들어있는 이벤트까지만 처리
			// 이벤트를 발생시킨다. 상단에 작성한 콜백(glfwSetKeyCallback)은 여기서 실행된다.
			glfwPollEvents();
		}
	}

	public static void main(String[] args) {
		new HelloWorld().run();
	}
}
