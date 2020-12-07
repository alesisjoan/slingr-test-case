from unittest import TestCase as TestCaseUT
from swagger_server.services.CalculatorServices import Calculator

calculator = Calculator()


class TestCalculator(TestCaseUT):
    def test_calculate(self):
        assert float(calculator.calculate("2+2", 0)) == 4
        assert float(calculator.calculate("3-2", 0)) == 1
        assert float(calculator.calculate("sqrt(2)", 2)) == 1.4
        assert float(calculator.calculate("2*2", 0)) == 4
        assert float(calculator.calculate("100/3", 2)) == 33
        assert float(calculator.calculate("2.5*2", 0)) == 5
        assert float(calculator.calculate("((2+3)*2)", 0)) == 10
        assert float(calculator.calculate("( ( 2 +3 ) * 2)", 0)) == 10

        error = calculator.calculate("import os", 0)
        assert error[0]['status_code'] == 400
        assert error[0]['error_list'] != ''
        assert error[1] == 400

        error2 = calculator.calculate("sqrt(2", 0)
        assert error2[0]['status_code'] == 400
        assert error2[0]['error_list'] != ''
        assert error2[1] == 400
