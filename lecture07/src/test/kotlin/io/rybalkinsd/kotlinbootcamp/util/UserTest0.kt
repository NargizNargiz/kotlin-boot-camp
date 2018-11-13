package io.rybalkinsd.kotlinbootcamp.util

import junit.framework.TestCase

class UserTest0 : TestCase() {

    fun testGetFirstName() {
        assertEquals("Alisa",User().firstName)
    }


}