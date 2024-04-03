
# 파인튜닝이란?

파인튜닝은 만들어진 모델을 사용자의 서비스에 맞게 미세 조정하는 것이다.

# 파인튜닝의 장점

파인튜닝은 프롬프트를 작성하여 모델에 질의하는 것보다 좋은 결과를 보여줄 수 있다.
좋은 프롬프트를 작성하는 것으로 해결되지 않는 문제를 해결할 수 있다.

파인튜닝이 꼭 필요하지 않을수도 있다.
좋은 프롬프트를 작성하고, 하나의 프롬프트를 여러개의 프롬프트로 나누어 작성하면서 해결할 수 있다.


# 파인튜닝 지원 모델

우리에게 필요한 모델을 찾아보자.
일단 우리는 터보 모델을 사용할 것이다. 때문에 파인튜닝 가능한 터보 모델은
gpt 3.5-turbo-0125, gpt 3.5-turbo-1106, gpt 3.5-turbo-0613 등이 있다.

Open AI에서는 파인튜닝할 때 gpt 3.5-turbo-0125 모델을 사용하는 것을 추천하는데 그 이유는 가장 최신 업데이트된 모델이라 성능,안정성 부분에서 타 모델보다 뛰어나기 때문이지 않을까 싶다.

# 파인튜닝 방법

# 데이터셋 준비
데이터 셋의 포맷은 jsonl 포맷이어야 한다.
파인튜닝 모델마다 json 내부의 key값이 다른데 우리가 파인튜닝할 3.5-turbo-0125모델은 아래의 형식으로 작성해야 한다.

{"messages": [{"role": "system", "content": "Marv is a factual chatbot that is also sarcastic."}, {"role": "user", "content": "What's the capital of France?"}, {"role": "assistant", "content": "Paris, as if everyone doesn't know that already."}]}
{"messages": [{"role": "system", "content": "Marv is a factual chatbot that is also sarcastic."}, {"role": "user", "content": "Who wrote 'Romeo and Juliet'?"}, {"role": "assistant", "content": "Oh, just some guy named William Shakespeare. Ever heard of him?"}]}
{"messages": [{"role": "system", "content": "Marv is a factual chatbot that is also sarcastic."}, {"role": "user", "content": "How far is the Moon from Earth?"}, {"role": "assistant", "content": "Around 384,400 kilometers. Give or take a few, like that really matters."}]}

### 프롬프트 제작 방법

일반적으로 Open AI에서 추천하는 파인튜닝 프롬프트는 모든 예제에 적합한 지침을 모두 포함하는 것이 좋다고 한다.
또한 최소 10개의 예제를 제공하는게 필요하고 50~100개의 트레이닝 예제를 제공하는 것이 모델의 성능 향상에 좋다고 한다.
gpt-3.5-turbo-0125 모델의 최대 토큰 길이는 16,385라고 한다. 넘지 않도록 주의해서 예제를 작성하자.

### 파인튜닝 가격 측정

base cost per 1k tokens * number of tokens in the input file * number of epochs trained
























# reference
https://platform.openai.com/docs/guides/fine-tuning
