import os
from openai import OpenAI

def main():
    key = os.getenv("OPENAI_API_KEY")
    client = OpenAI(api_key = key)
    
    while True:
        user_input = input("You: ")

        if user_input.lower() in ["quit", "exit"]:
            print("Goodbye!")
            break

        response = client.chat.completions.create(
            model="gpt-4.1",
            messages=[
                {"role": "system", "content": "You are a helpful assistant."},
                {"role": "user", "content": user_input}
            ]
        )

        reply = response.choices[0].message.content
        print(f"AI: {reply}\n")

if __name__ == "__main__":
    main()
